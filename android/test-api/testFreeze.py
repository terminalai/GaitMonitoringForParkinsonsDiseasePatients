import requests

url = 'https://gait-analyzer-1ac2c.web.app/freeze'

#url = "https://us-central1-gait-analyzer-1ac2c.cloudfunctions.net/freeze"

data = {
    'freezeY': 10.0,
    'freezeZ': 11.0
}

x = requests.post(url, data=data)

print(x) # <Response [200]>
print(x.status_code) # 200

with open("text.txt", "w+") as file:
    print(x.text, file=file)
