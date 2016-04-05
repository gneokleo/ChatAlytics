#ChatAlytics

THIS REPOSITORY HAS BEEN MOVED TO www.github.com/OpenChatAlytics

FUTURE COMMITS WILL GO THERE FROM NOW ON

[![Gitter](https://badges.gitter.im/gneokleo/ChatAlytics.svg)](https://gitter.im/gneokleo/ChatAlytics?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

##Short Summary
ChatAlytics is a realtime platform for processing HipChat and Slack messages using Storm as the processing framework. Implementors can connect their own models into the topology for coming up with metrics. Those metrics can then be accessed through a REST API that ChatAlytics provides. This project also contains [HuBot](https://github.com/hipchat/hubot-hipchat) coffee scripts that hit the endpoints in ChatAlytics.

##Building the Project
To build the example you can just run

`mvn clean package`

You may need to also download the caseless models from the [Stanford Core NLP](http://nlp.stanford.edu/software/corenlp.shtml) website. The default one used in the config is named: english.all.3class.distsim.crf.ser.gz.

##Running in Docker mode
Navigate to the top level directory where the Dockerfile is and run the following command:
`docker build -t chatalytics ./`

This will build a new Docker image named chatalytics. You can get a list of all the images by running:
`docker images`

To start the container in the backgroung based on that image run:
`docker run -d --name chatalytics chatalytics`

You should be able to see the container running by executing:
`docker ps`

To ssh into the container run:
`docker exec -i -t chatalytics bash`

To stop the container run:
`docker stop`

To remove old containers run:
`docker rm chatalytics`

##Author
Giannis Neokleous

www.giann.is

