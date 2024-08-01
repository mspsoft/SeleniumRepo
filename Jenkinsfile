pipeline {
    agent any
    
     stages {
        stage('Build code') {
            steps {
                bat 'mvn compile'
            }
        }
        stage('Run Test') {
            steps{
                bat 'mvn test'
            }
        }
        stage('Post Build') {
            steps {
                script {
                    env.WORKSPACE = pwd()
                    def version = readFile "${env.WORKSPACE}/extent_report_path.txt"
                    // def extentReportPath = readFile('extent_report_path.txt').trim()
                      echo "In post build....Extent Report Path version: ${version}"
                     echo "now finding the filename from the path"
                    
                      def fileName = version.substring(version.length() - 12)
                       echo "File Name is ${fileName}"
                    def pathlength= version.length()-12
                      echo "Length of the filepath is ${pathlength}"
                      def filepath = version.substring(0,version.length() - 12)

                       echo "File path is ${filepath}"

                       //publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: '', reportFiles: "${extentLogFilePath}", reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
                        publishHTML([
                            reportName: 'Extent Report',
                            reportDir: filepath,
                            reportFiles: fileName,
                            keepAll: true,
                          alwaysLinkToLastBuild: true,
                           allowMissing: false
                        ])
                }
            }
        }
    }
  
}