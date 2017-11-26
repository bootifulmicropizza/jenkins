import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl

Closure setCredsIfMissing = { String id, String descr, String user, String pass ->
	boolean credsMissing = SystemCredentialsProvider.getInstance().getCredentials().findAll {
		it.getDescriptor().getId() == id
	}.empty
	if (credsMissing) {
		println "Credential [${id}] is missing - will create it"
		SystemCredentialsProvider.getInstance().getCredentials().add(
			new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, id,
				descr, user, pass))
		SystemCredentialsProvider.getInstance().save()
	}
}

setCredsIfMissing("GitHub", "GitHub credential", "username", "password")
setCredsIfMissing("DockerHub", "DockerHub credential", "username", "password")
