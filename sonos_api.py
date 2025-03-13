import sys
import requests
import re

def validate_device_id(device_id):
    """Validate that the device ID is exactly 10 hexadecimal characters."""
    return re.match(r'^[0-9a-fA-F]{10}$', device_id) is not None

def make_api_request(ip, device_id, switch):
    """Make a REST API request based on the provided arguments."""
    url = f"http://{ip}:8081/zeroconf/switch"
    payload = {
        "deviceid": device_id,
        "data": {
            "switch": switch.lower()
        }
    }

    if switch.upper() == "CONF":
        url = f"http://{ip}:8081/zeroconf/info"
        payload = {
            "deviceid": device_id,
            "data": {}
        }

    try:
        response = requests.post(url, json=payload)
        response.raise_for_status()  # Raise an error for bad status codes
        print("Request successful!")
        print("Response:", response.json())
    except requests.exceptions.RequestException as e:
        print("Request failed:", e)

def main():
    # Check if the correct number of arguments is provided
    if len(sys.argv) != 4:
        print("Error: Invalid number of arguments.")
        print("Usage: python script.py <IP> <deviceID> <ON|OFF|CONF>")
        sys.exit(1)

    ip = sys.argv[1]
    device_id = sys.argv[2]
    switch = sys.argv[3].upper()

    # Validate the device ID
    if not validate_device_id(device_id):
        print("Error: deviceID must be exactly 10 hexadecimal characters.")
        sys.exit(1)

    # Validate the switch argument
    if switch not in ["ON", "OFF", "CONF"]:
        print("Error: Third argument must be ON, OFF, or CONF.")
        sys.exit(1)

    # Make the API request
    make_api_request(ip, device_id, switch)

if __name__ == "__main__":
    main()