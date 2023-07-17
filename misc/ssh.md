# SSH

- [SSH](#ssh)
  - [MISC](#misc)
  - [Rapid Setup](#rapid-setup)
  - [Authentication and SSH keys](#authentication-and-ssh-keys)
    - [Generating SSH keys](#generating-ssh-keys)
  - [Server side things](#server-side-things)

## MISC

- [DigitalOcean article on SSH](https://www.digitalocean.com/community/tutorials/ssh-essentials-working-with-ssh-servers-clients-and-keys#ssh-overview)
- It is a protocol and is used to control Linux servers remotely
  - It is text based and encrypted
- It follows client-server model. Thus server should have a **SSH daemon** running that listens for connections and client should have a software that can communicate over SSH protocol
- **/etc/ssh/ssh_config** is config for client side and **/etc/ssh/sshd_config** is config for server side.

## Rapid Setup

- Client:
  1. `ssh-keygen` to generate keys
  2. Copy **~/.ssh/id_rsa.pub** to server
  3. SETUP SERVER
  4. `ssh [-i private_key] remote_user@remote_ip -p port` to ssh into server

- Server:
  1. `cat id_rsa.pub >> ~/.ssh/authorized_keys` to setup keys
  2. `ifconfig` to the ip
  3. `whoami` to get the username
  4. `cat /etc/sshd_config` for port number
  5. `sshd -E log_file && tail -f log_file` to start ssh daemon with logs

- Misc:
  1. Remember to disable password auth on server side in **/etc/ssh/sshd_config**

## Authentication and SSH keys

- Authentication can be done via two ways:
  1. **Passwords**: (NOT PREFERRED)
  2. **SSH Keys**: (PREFERRED)
- SSH keys contain two keys:
  1. **Public key:** Stored on remote server in **~/.ssh/authorized_keys**. Each line contains a public key
  2. **Private key:** Stored on client. Can be stored anywhere however **~/.ssh/id_rsa** is default location

### Generating SSH keys

- These can be generated using any cryptographic algorithms like RSA (default), DSA, ECDSA, etc.
- Leaving *filename* empty will allow SSH client to find keys automatically
- Leaving *passphrase* empty will allow anyone with private key to gain access of your server
- `ssh-keygen -p -f private_key_file` will allow you to change passphrase of a private key
- `ssh-keygen -l -f private/public_key_file` will display you **bit-length fingerprint account@hostname (algorithm used)**. Fingerprint uniquely identifies the keys. Both public/private key have same fingerprint.
- NOTE: Identification key and private key are same things

```bash
> ssh-keygen
Generating public/private rsa key pair.
Enter file in which to save the key (/home/sickboy/.ssh/id_rsa): termux_rsa
Enter passphrase (empty for no passphrase):
Your identification has been saved in termux_rsa
Your public key has been saved in termux_rsa.pub
The key fingerprint is:
       ...
```

## Server side things

- `ps -A | grep sshd` will tell if any sshd daemon is running
- You can use `kill <pid>` or `pkill sshd` to kill sshd daemon if you want to
- `sudo service sshd restart` can be used to restart an ssh daemon
- Use `man 5 sshd_config` for all available options of configuring `/etc/ssh/sshd_config`. Some important ones are below:
  1. **PasswordAuthentication yes/no**: Allow to connect via password (keep disabled)
  2. **Banner /etc/issue.net**: Will display a message (usually legal notice) to the client before authentication
  3. **PrintMotd yes/no**: Whether to print motd or not