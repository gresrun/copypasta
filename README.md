CopyPasta
=========

A simple, websocket-based, chat-esque solution to copy-paste problems in RDP and/or VMWare.

Uses embedded [Jetty](http://www.eclipse.org/jetty/) and is packaged as an executable JAR using [Maven](http://maven.apache.org/) Shade Plugin.

***

How do I use it?
----------------

Download the latest source at:

	https://github.com/gresrun/copypasta

and run it:

	mvn clean package
	java -jar target/copypasta.jar 8080

where `8080` is the port that Jetty will listen on.

Then open a browser in each machine you want to send text between and copypasta away!

***

License
-------
Copyright 2011 Greg Haines

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

