import hudson.tasks.Maven
import hudson.tasks.Maven.MavenInstallation;
import hudson.tools.InstallSourceProperty;
import hudson.tools.ToolProperty;
import hudson.tools.ToolPropertyDescriptor
import hudson.tools.ZipExtractionInstaller;
import hudson.util.DescribableList
import jenkins.model.Jenkins;

def extensions = Jenkins.instance.getExtensionList(Maven.DescriptorImpl.class)[0]

List<MavenInstallation> installations = []

mavenToool = ['name': 'Maven', 'url': 'http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz', 'subdir': 'apache-maven-3.5.2']

println("Setting up tool: ${mavenToool.name} ")

def describableList = new DescribableList<ToolProperty<?>, ToolPropertyDescriptor>()
def installer = new ZipExtractionInstaller(mavenToool.label as String, mavenToool.url as String, mavenToool.subdir as String);

describableList.add(new InstallSourceProperty([installer]))

installations.add(new MavenInstallation(mavenToool.name as String, "", describableList))

extensions.setInstallations(installations.toArray(new MavenInstallation[installations.size()]))
extensions.save()