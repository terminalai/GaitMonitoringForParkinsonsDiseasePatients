import requests

url = 'https://gait-analyzer-1ac2c.web.app/app/freeze'
data = {
    'freezeY': 10.0,
    'freezeZ': 11.0
}

x = requests.post(url, data=data)

print(x) # <Response [200]>
print(x.status_code) # 200
print(x.text) #
