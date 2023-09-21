def call() {
    try {
             def apiUrl = "https://api.github.com/orgs/${GITHUB_ORG}/repos"
                    def payload = [
                        name: NEW_REPO_NAME,
                        description: 'fourthrepo',
                        private: false,
                        auto_init: true
                    ]

                    def response = httpRequest(
                        acceptType: 'APPLICATION_JSON',
                        contentType: 'APPLICATION_JSON',
                        httpMode: 'POST',
                        url: apiUrl,
                        requestBody: groovy.json.JsonOutput.toJson(payload),
                        authentication: 'PAT2'
                    )

                    if (response.status == 201) {
                        echo "GitHub repository '${NEW_REPO_NAME}' created successfully."
                    } else {
                        error "Failed to create GitHub repository. Status code: ${response.status}"
                    }
        } catch (Exception e) {
            return "Error creating repository: ${e.message}"
        }
}
