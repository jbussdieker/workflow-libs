import jenkins.model.Jenkins
import jenkins.security.s2m.AdminWhitelistRule
import hudson.security.csrf.DefaultCrumbIssuer
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm
import jenkins.model.JenkinsLocationConfiguration

Jenkins.instance.getDescriptor("jenkins.CLI").get().setEnabled(false)
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)
Jenkins.instance.setCrumbIssuer(new DefaultCrumbIssuer(true))

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.denyAnonymousReadAccess = true
Jenkins.instance.setAuthorizationStrategy(strategy)
Jenkins.instance.save()

def realm = new HudsonPrivateSecurityRealm(false)
realm.createAccount("admin", "admin")
Jenkins.instance.setSecurityRealm(realm)

def config = JenkinsLocationConfiguration.get()
config.setUrl("http://localhost:8080/")
config.save()
