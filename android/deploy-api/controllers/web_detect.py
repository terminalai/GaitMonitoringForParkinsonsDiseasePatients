#!/usr/bin/env python

# Copyright 2017 Google Inc. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# imitations under the License.

"""Demonstrates web detection using the Google Cloud Vision API.
Example usage:
  python web_detect.py https://goo.gl/X4qcB6
  python web_detect.py ../detect/resources/landmark.jpg
  python web_detect.py gs://your-bucket/image.png
"""
# [START full_tutorial]
# [START imports]
import argparse
import io
import os

from google.cloud import vision
from google.cloud.vision import types

from helperfunctions import get_disease_dicts
# [END imports]

from google.oauth2 import service_account
credentials = service_account.Credentials.from_service_account_file(os.path.dirname(os.path.abspath(__file__))+'/api-key/Hackeam-fde790441b8d.json')

scoped_credentials = credentials.with_scopes(
['https://www.googleapis.com/auth/cloud-platform'])

def annotate(path):
    """Returns web annotations given the path to an image."""
    # [START get_annotations]
    client = vision.ImageAnnotatorClient()

    if path.startswith('http') or path.startswith('gs:'):
        image = types.Image()
        image.source.image_uri = path

    else:
        with io.open(path, 'rb') as image_file:
            content = image_file.read()

        image = types.Image(content=content)

    web_detection = client.web_detection(image=image).web_detection
    # [END get_annotations]

    return web_detection


def report(annotations):
    """Prints detected features in the provided web annotations."""
    tag_list = []
    # [START print_annotations]
    if annotations.web_entities:
        for entity in annotations.web_entities:
            tag_list.append(entity.description)
            # print('Score      : {}'.format(entity.score))
            # print('Description: {}'.format(entity.description))
    # [END print_annotations]
    print get_disease_dicts(tag_list)


if __name__ == '__main__':
    # [START run_web]
    parser = argparse.ArgumentParser(
        description=__doc__,
        formatter_class=argparse.RawDescriptionHelpFormatter)
    path_help = str('The image to detect, can be web URI, '
                    'Google Cloud Storage, or path to local file.')
    parser.add_argument('image_url', help=path_help)
    args = parser.parse_args()

    report(annotate(args.image_url))
    # [END run_web]
# [END full_tutorial]
