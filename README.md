# Oneratus

A full mc client loader using tweakclass and remote class loading

## Usage

### Structure

* `loader` – This module loads the client code from the server
* `client` – the client code, This gets loaded from the server using the loader when the game starts.
* `server` – authenticating hwid and username, gets client.jar in return if valid
* `installer` – installs the libary jar and edits the version.json files (adds tweakclass)

### Development
1. Build your loader jar

2. Change the path in the installer code to dowload your library

3. Add usernames and hwids to the server and run it
4. change the url in the loader

(ik its a really bad explanation but you have to understand simple code to use this || NOT SKID FRIENDLY || )

## Credits
earthhack - parts of the installer

(https://github.com/ThnksCJ/logger-plus) - My logging package

orsond - he gave me his loader so some of the code is from that (mainly the instance launch thingy in the loader)