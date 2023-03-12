# C1: Computer Networks and the Internet

- **Internet**: Computer network that interconnects billions of computing devices
- **Hosts/End Systems**: Devices that are connected to internet
    1. *Clients*: Receiver hosts
    2. *Servers*: Sender hosts (tend to be more powerful machines)
- End systems are connected together by:
    1. *Communication Links* (like highways) and
    2. *Packet Switches* (like intersections). Prominent types:
        - Routers
        - Link-Layer Switches
- **Route** is the path of packet

---

- End systems access internet through ISPs (Internet Service Providers).
- **Internet socket interface** is a set of rules that a sending program must follow so that the internet can deliver the data to the destination program.
- **Protocol**: Set of rules defining the format and order of messages for communicating with other entities. These rules also define the actions to be taken when sending or receiving messages.

---

> - **Data Center**: Collection of interconnected hosts (servers in this case).
> - Hosts are called **Blades** in Data Centers and are stacked in racks (20 to 40 in each rack)

---

## Access Networks

- **Access Network**: Network that physically connects an end system to first router (called edge router)

1. Prominent types of broadband residential access:
   1. **Digital Subscriber Line (DSL)**: Telephone line carries both telephone signals and data encoded at different frequencies. On customer side, **splitter** and on telco side DSLAM separates the data and phone signals. Works best within 5 to 10 miles. ADSL and SDSL are two types of DSL.
      1. 0 to 4kHz for telephone-channel
      2. 4kHz to 50 kHz for medium-speed upstream
      3. 50kHz to 1 MHz for high-speed downstream
   2. **Cable**: Television cables carry both data and television signals. Also called HFC (Hybrid Fiber Coax) system as both fiber optics and coaxial cables are used.
      1. Cable Modem: Physical device that connect to PC through Ethernet Port.
   3. **Fiber to the Home (FTTH)**: Up-and-coming tech providing speed in Gbps.
2. LAN
   - Hosts are connected by LAN to the edge router.
   - Types:
     1. **Ethernet**: End systems connect with ethernet switch which is then in turn connected to internet. High speed access.
     2. **WiFi (Wireless Fidelity)**: Wireless LAN. User is within tens of meters from access point (tower?) which is inturn connected to enterprise's network via wired Ethernet.
3. Wide-Area Wireless Access: 3G & LTE 4G and 5G
   - Used in mobile devices
   - Uses infrastructure of cellular telephone
   - Users need to be within few tens of kilometers

> Typically, users combine Wireless LAN with broadband residential access (cable and DSL)

## Physical Media

- Two types:
   1. Guided Media: Fiber-optic cable, copper wire, coaxial cable
   2. Unguided Media: Wireless LAN, digital satellite channel

> Material for physical links is far less expensive than its installation

1. **Twisted-Pair Copper Wire**:
    - One wire pair constitutes a single communication link.
