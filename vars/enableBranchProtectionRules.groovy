def call(String GITHUB_ORG, String NEW_REPO_NAME, String BRANCH_NAME) {
    try {
             wrap([$class: 'BuildUser']){
          //repos = params.gh_personal_repo.readLines()
          //repos.each { String repo ->
            def response = httpRequest authentication: "PAT2", customHeaders: [[name: 'Accept', value: 'application/vnd.github.v3+json']], contentType: "APPLICATION_JSON", httpMode: "PUT", ignoreSslErrors: true,
requestBody: """{"enforce_admins": false, "required_pull_request_reviews": {"require_code_owner_reviews": true, "dismiss_stale_reviews": true}, "required_status_checks": {"strict": true, "contexts": ["continuous-integration/jenkins/branch","continuous-integration/jenkins/pr-merge"]}, "restrictions": {"users": ["null"], "teams": ["null"]}}""",
responseHandle: "STRING", url: "https://api.github.com/repos/${GITHUB_ORG}/${NEW_REPO_NAME}/branches/${MASTER_BRANCH}/protection", validResponseCodes: '100:599'
            println ("STATUS: "+response.status)
            println ("CONTENT: "+response.content)
          //}
        	}

                    if (response.status == 201) {
                        echo "Rules enabled successfully on ${BRANCH_NAME}"
                    } else {
                        error "Failed to enable rules, Status code: ${response.status}"
                    }
        } catch (Exception e) {
            return "Error creating repository: ${e.message}"
        }
}
