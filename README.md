# AutoFinancials [![Build Status](https://travis-ci.com/TriedAngle/AutoFinancials.svg?branch=master)](https://travis-ci.com/TriedAngle/AutoFinancials)  [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Table of Contents

- Installation
    - Prerequisites
        - Java
            - Windows
            - OSX
            - Ubuntu
            - Arch
            
        - Libraries
            - Adding Libraries (IntelliJ & Eclipse)
            - List
    - Configure Database
    - Configure Application
- Usage
    - coming soon
- Contributing

## Installation

### Prerequisites
JDK 11 and gradle is required for this project.
While a guide for a java installation is written here, gradle is not.
To install gradle I personally recommend SDK MAN found here

##### Windows:
coming soon

##### Max OSX:
coming soon

##### Ubuntu:
```bash
$ sudo apt update && apt-get upgrade
$ sudo apt install software-properties-common
$ sudo add-apt-repository ppa:linuxuprising/java
$ sudo apt install oracle-java11-installer
```

##### Arch:
```bash
$ sudo pacman -S jre-11-openjdk
$ sudo archlinux-java status

Available Java environments:
    java-7-openjdk (default)
    java-8-openjdk/jre
    java-11-openjdk/jre
    
$ archlinux-java-set java-11-openjdk/jre
```
### Installation

As gradle is used now, simply type 
```
gradle build
```
### Configure Application

No configuration needed anymore as all options are now contained in the application gui

## Usage
A guide will be coming soon

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.
A small project which automates the financial work for my schools organisation team
