# Oneratus

A full mc client loader using tweakclass and remote class loading


### Structure

* `loader` – This module loads the client code from the server
* `client` – the client code, This gets loaded from the server using the loader when the game starts.
* `server` – authenticating hwid and username, gets client.jar in return if valid
* `installer` – installs the libary jar and edits the version.json files (adds tweakclass)
