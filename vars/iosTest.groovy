def call(Map config) {

  pipeline {
    def coverageFilePath = config.coverageFilePath
    
	checkOutAndLint(config)

    stage ('Unit Test') {
        sh "bundle exec fastlane sdk_test"
        cobertura coberturaReportFile: coverageFilePath
    }
  }
}
