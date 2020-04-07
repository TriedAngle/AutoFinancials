# AutoFinancials [![Build Status](https://travis-ci.com/TriedAngle/AutoFinancials.svg?branch=master)](https://travis-ci.com/TriedAngle/AutoFinancials)  [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Table of Contents

- Installation
    - Prerequisites
	- System:
		- Windows (10)
		- macOS
		- Ubuntu Linux
		- Debian Linux
		- Arch Linux
        - Java 11
        - Libraries
            - Adding Libraries (IntelliJ & Eclipse)
            - List
    - Configure Database
    - Configure Application
- Usage
    - Coming soon
- Contributing

## Installation

### Prerequisites
JDK 11 and gradle are required for this project to work properly.
While proper install instructions for Java are described down below, some parts may vary from machine to machine.

##### Windows:
Download and install your proper JDK 11 version from here: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
Follow these steps to install gradle on your windows machine: https://gradle.org/install/

##### macOS:
Download and install your proper JDK 11 version from here: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
Follow these steps to install gradle on your macOS machine: https://gradle.org/install/

##### Ubuntu Linux:
```
$ sudo apt update && sudo apt upgrade && sudo apt dist-upgrade
$ sudo apt install apt-transport-https dirmngr wget software-properties-common
$ sudo add-apt-repository ppa:linuxuprising/java
$ sudo add-apt-repository ppa:cwchien/gradle
$ sudo apt -y install oracle-java11-installer gradle
```
##### Debian Linux:
```
$ sudo apt update && sudo apt upgrade && sudo apt dist-upgrade
$ sudo apt -y install gnupg2
$ sudo apt -y install vim apt-transport-https dirmngr wget software-properties-common
$ sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys D7CC6F019D06AF36
$ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 73C3DB2A
$ sudo add-apt-repository ppa:cwchien/gradle
$ echo 'deb http://ppa.launchpad.net/linuxuprising/java/ubuntu focal main' | sudo tee -a /etc/apt/sources.list.d/linuxuprising-java.list
$ sudo apt -y install oracle-java11-installer-local gradle
```



##### Arch Linux:
```
$ sudo pacman -Syu jdk-11-openjdk gradle
$ sudo archlinux-java status

Available Java environments:
    java-7-openjdk (default)
    java-8-openjdk
    java-11-openjdk
    
$ archlinux-java set java-11-openjdk
```
### Installation

As gradle is installed, it can be invoked by
```
gradle build
```

## Usage
A guide will be added soon

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

### Facilitates the financial management of our school employees
