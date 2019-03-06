def call(Map config) {

  pipeline {
    def credentialsId = config.credentialsId
    def gitUri = config.gitUri
    def artifactoryPropertiesFile = config.artifactoryPropertiesFile
    def gitBranch = config.gitBranch ?: 'master'

    if (gitUri == null || credentialsId == null || artifactoryPropertiesFile == null) {
        throw new IllegalStateException('Missing configuration arguments')
    }

    stage ('Checkout') {
        git credentialsId: credentialsId, url: gitUri, branch: gitBranch
    }

    stage ('Fetch Dependencies') {
        sh "bundle exec fastlane bootstrap"
    }

    stage ('Deploy') {
        sh "bundle exec fastlane build_deploy_framework config_name:${artifactoryPropertiesFile}"
    }
  }
}