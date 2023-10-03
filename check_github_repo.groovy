
def forceSkip=false

pipeline {
    agent any

    environment {
       // GITHUB_TOKEN = credentials('PAT-Maddu')
        GITHUB_ORG = 'devopstechskills'
        NEW_REPO_NAME = 'dummyrepo11'
        BRANCH_NAME='main'
    }

    stages {
        stage('Check and Create Git Branch') {
            steps {
                script {
                    //def branchExists = false
                   def apiUrl = "https://api.github.com/repos/${GITHUB_ORG}/${NEW_REPO_NAME}"
                   //def GITHUB_TOKEN = credentials('PAT-Maddu')

                    // Check if the GitHub repository exists
                    def checkResponse = httpRequest(
                        httpMode: 'GET',
                        url: apiUrl,
                        acceptType: 'APPLICATION_JSON',
                        //requestBody: groovy.json.JsonOutput.toJson(payload),
                        authentication: 'PAT-Maddu'

                    )
                    
                    echo "code: ${checkResponse.status}"

                    if (checkResponse.status == 200) {
                        echo "GitHub repository '${GITHUB_ORG}/${NEW_REPO_NAME}' exists."
                    } else {
                        echo "GitHub repository '${GITHUB_ORG}/${NEW_REPO_NAME}' does not exist."
                    }

                    // Now you can perform actions on the branch
                }
            }
        }
       
    }
}

