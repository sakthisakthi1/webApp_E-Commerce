#!/usr/bin/env groovy
def DOCKER_REGISTRY = "760440466011.dkr.ecr.ap-south-1.amazonaws.com"
def projectName = "jd-adzone-dev-orderservice".toLowerCase()
def MajorVersion = "1"
def scmRes
def TAG = "latest"
def revision
def appImage
def mvnProfile = 'Devl'
def dockerFileName
def allowedNotificationEnvs = ['dev','qual','cert','prod']
pipeline {
	agent any
		tools {
			maven 'maven'
		}
    	
		stages {
			stage('SetupParameters'){
				
				steps{
					script{
						properties([
							parameters([
								choice(name: 'ENV',description: 'Enviroment',choices: ['devl','qual','cert','prod'])
							])
						])
					}
				}
			}
			stage('SetupVariables'){
			
				steps{
					echo 'set variable'
					script {
				    	if("${params.ENV}" == 'devl'){
				    		DOCKER_REGISTRY = '760440466011.dkr.ecr.ap-south-1.amazonaws.com'
				    		projectName = "jd-adzone-dev-orderservice".toLowerCase()
				    		mvnProfile = 'Devl'
						dockerFileName = 'Dockerfile-Devl'
				    	}else if("${params.ENV}" == 'qual'){
				    		DOCKER_REGISTRY = '760440466011.dkr.ecr.ap-south-1.amazonaws.com'
				    		projectName = "adzone-qual-backend".toLowerCase()
				    		mvnProfile = 'Qual'
						dockerFileName = 'Dockerfile-Qual'
				    	}else if("${params.ENV}" == 'cert'){
				    		DOCKER_REGISTRY = '760440466011.dkr.ecr.ap-south-1.amazonaws.com'
				    		projectName = "adzone-cert-backend".toLowerCase()
				    		mvnProfile = 'Cert'
						dockerFileName = 'Dockerfile-Cert'
				    	}else if("${params.ENV}" == 'prod'){
							DOCKER_REGISTRY = '760440466011.dkr.ecr.ap-south-1.amazonaws.com'
							projectName = "jd-adzone-dev-orderservice".toLowerCase()
							mvnProfile = 'Prod'
							dockerFileName = 'Dockerfile-Prod'
						}
				    }
				}
			}
			stage('Checkout') {
			
				steps {
					echo 'package checkout'
					script {
						deleteDir()
				        scmRes =  checkout scm
				      	 ID = UUID.randomUUID().toString()[-12..-1]
				       	 APP_TAG = "app_${MajorVersion}.${currentBuild.id}-${ID}"
					  revision = "${currentBuild.id}"
					}
				}
		    }

			
       stage('RemoveGitDirectory') {

	   steps {
        sh "echo '${scmRes.GIT_COMMIT}' > .revision"
        sh 'rm -fr .git'
    }
	}

			stage('Package') {
				
	            steps{
	            	echo 'package stage'
	              	sh 'mvn clean install --s settings.xml -Dspring.profiles.active='+mvnProfile
	            }
		    }
		
		    stage('Build') {
		    
		    	steps {
		    		echo 'package build'
		    		script {
					appImage = docker.build("${DOCKER_REGISTRY}/${projectName}:${APP_TAG}", '--no-cache -f '+"${dockerFileName}"+' .')
		    		}
		    	}
		    }
		
				   stage('Docker :: Push') {
		     steps{
		                 script {
				docker.withRegistry(
				'https://760440466011.dkr.ecr.ap-south-1.amazonaws.com',
				'ecr:ap-south-1:AWS-ECR') {
		        appImage.push('latest')
				}
			}
		      }
		 }
		
		    stage('Docker :: Remove Image') {
		    	
		    	steps {
		        	sh "docker rmi -f ${DOCKER_REGISTRY}/${projectName}:${APP_TAG}"
		        }
		     }
				
		}
}
