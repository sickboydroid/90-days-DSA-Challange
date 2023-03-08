# Network CC

Videos:
- [#1 Computer Networks: The beginning](https://youtu.be/3QhU9jd03a0)
- [#2 Computer Networks: ](https://youtu.be/3QhU9jd03a0)
- [#3 Computer Networks](https://youtu.be/3QhU9jd03a0)

## Local Area Network (LAN) 
 
- It is a relatively small networks of close-by computers
- The most famous LAN Tech is `Ethernet` and `WIFI`
  
### Ethernet

- Series of computers connected via single ethernet cable
- Each computer is assigned a unique `MAC (Media Access Control) Address`.
- The sender puts MAC address of receiver in the header.
- Everyone receives message but only that computer responds which has the header's MAC addr.

- WIFI and Ethernet both have unique MAC address

- The general term for this approach is **Carrier Sense Multiple Access (CSMA)**. In ethernet, carrier is copper wire while in wifi it is air
  - The rate at which a carrier can transmit data is called its `Bandwidth`
- If ethernet detects a **collison** it waits for a random short period of time. 
  - The "backing off" behavior using an exponentially growing wait time is called `Exponential Backoff`.
- To reduce collisions and improve efficiency, number of devices are reduced on shared carrier -- `Collision Domain`.
  - `Network switch` is used to connect two smaller networks. It only passes data b/w them if necessary
    - It stores the list of all MAC addresses on either side of it
    - Internet means a bunch of smaller inter-connected networks. It works on same principle

- Other than LAN there are `wide area network (WAN)` and `metropolitan area network (MAN)` that cover larger geographic areas. Some WANs and MANs connect many smaller LANs

## Routing

### Circuit Switching

- Dedicated single route from A to B
- For example old telephone network, sender asked the customer care to join him with other recevier
 
### Message Switching

- Several stops b/w A to B.
- Each stop knows where to send the message given the destination address. More reliable and efficient.
- The hops/jumps a message takes along the route -> `Hop Count`
- `Hop Limit` ensures that we are not running in circles

### Packet Switching

- Big transmitions are choped into many small pieces called `Packets`
- Chopping up date into small packets and passing these along flexible routes with spare capacity is very efficient. It is what the whole internet runs on today. This routing approach is called `packet switching`.
- **ARPANET** was world's first packet switched network. It is the ancestor to the modern internet


- Each packet has a destination address on it. The format is defined by `IP (Internet Protocol)`. Every device connected to internet gets an IP Addr.
- Network routers constantly try to ensure speedy and reliable delivery which is called `Congestion Control`.
- Sometimes packets take different routes and thus receiver can receive them out of order. To address this issue some special transmission protocals run on top IP e.g `TCP/IP`.
