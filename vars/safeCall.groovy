def call(Closure closure) {
   try {
     slackSend color: '#ffff00', message: """
     ${env.JOB_NAME}
     Build: #${env.BUILD_NUMBER}
     Status: Started
     See: ${BUILD_URL}
     """;

     closure();

     slackSend color: 'good', message: """
     ${env.JOB_NAME}
     Build: #${env.BUILD_NUMBER}
     Status: Succeded
     See: ${BUILD_URL}
     """;
   }
   catch (Exception | AssertionError exc) {
     slackSend color: '#ff0000', message: """
     ${env.JOB_NAME}
     Build: #${env.BUILD_NUMBER}
     Status: Failed
     See: ${BUILD_URL}
     """;
   }
}
