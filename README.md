#How to Setup and Control Sonos Mini R2 for Direct Connection 

##Set Device into DIY Mode 
The first step is to set the device into DIY Mode allowing you to directly control the device. 

##REST API 
https://sonoff.tech/sonoff-diy-developer-documentation-basicr3-rfr3-mini-http-api/ 

##Entering DIY Mode:
•	Power on the Sonoff Mini R2. 
•	Long press the button on the device for approximately 5 seconds. 
•	The Wi-Fi LED indicator should change to a cycle of two short flashes and one long flash. 
•	Release the button. 

##Connecting to the Access Point: 
•	Find the Access Point (AP) named "ITead-XXXXXXXX" in your phone or computer's Wi-Fi settings. 
•	Connect to the AP using the default password: "12345678". 
•	Accessing the DIY Webpage:
•	Open a web browser on your phone or computer. 
•	Type the IP address http://10.10.7.1/ into the browser's address bar. 
##IMPORTANT – Write down the XXXXX – this is the device ID. 

##Configuring Wi-Fi:
•	On the DIY webpage, you'll find the Wi-Fi settings. 
•	Enter your desired Wi-Fi SSID (network name) and password. 
•	The device will then connect to your Wi-Fi network. 

##DIY Mode Functionality:
•	Once connected to your Wi-Fi, the Sonoff Mini R2 is in DIY mode, allowing you to control it via HTTP commands or integrate it with other home automation platforms.
•	You need to find the IP Address of the Device

##Important
You need to know the IP Address and Device ID to access the device. 

##Checking Access using Postman
http://192.168.3.180:8081/zeroconf/info
{
    "deviceid": "1002431fd5",
    "data": { 
    }
}

Response
{
    "seq": 4,
    "error": 0,
    "data": {
        "switch": "off",
        "startup": "off",
        "pulse": "off",
        "pulseWidth": 500,
        "ssid": "HUAWEI-WHAKA",
        "otaUnlock": false,
        "fwVersion": "3.8.0",
        "deviceid": "1002431fd5",
        "bssid": "88:81:b9:8f:d6:4",
        "signalStrength": -55
    }
}


##Turning the Switch on (HTTP POST command)
http://192.168.3.180:8081/zeroconf/switch
{
    "deviceid": "1002431fd5",
    "data": { 
        "switch": "on" 
    }
}

Response
{
    "seq": 3,
    "error": 0
}


##Turning the Switch Off
http://192.168.3.180:8081/zeroconf/switch
{
    "deviceid": "1002431fd5",
    "data": { 
        "switch": "off" 
    }
}

Response
{
    "seq": 3,
    "error": 0
}


