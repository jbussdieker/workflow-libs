FROM jenkins/jenkins:2.164.2

RUN /usr/local/bin/install-plugins.sh workflow-aggregator:2.6
RUN /usr/local/bin/install-plugins.sh github-branch-source:2.5.1
RUN /usr/local/bin/install-plugins.sh ansicolor:0.5.3

RUN echo 2.164.2 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.164.2 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

COPY setup.groovy /usr/share/jenkins/ref/init.groovy.d/
