folder('CI-Pipelines') {
    displayName('CI-Pipelines')
    description('CI-Pipelines')
}

/// Simple Syntax

//pipelineJob('CI-Pipelines/frontend') {
//  configure { flowdefinition ->
//    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//        'userRemoteConfigs' {
//          'hudson.plugins.git.UserRemoteConfig' {
//            'url'('https://zeeshanshaik1203@dev.azure.com/zeeshanshaik1203/Roboshop/_git/frontend')
//          }
//        }
//        'branches' {
//          'hudson.plugins.git.BranchSpec' {
//            'name'('*/main')
//          }
//        }
//      }
//      'scriptPath'('Jenkinsfile')
//      'lightweight'(true)
//    }
//  }
//}

/// RUnning a loop to lkeep the code dry.
def component = ["cart", "catalogue","user","payment","shipping","frontend"];

def count=(component.size()-1)
for (i in 0..count) {
    def j=component[i]
    pipelineJob("CI-Pipelines/${j}") {
        configure { flowdefinition ->
//            flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
//                'triggers' {
//                    'hudson.triggers.SCMTrigger' {
//                        'spec'('*/2 * * * *')
//                        'ignorePostCommitHooks'(false)
//                    }
//                }
//            }
            flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
                'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'('https://zeeshanshaik1203@dev.azure.com/zeeshanshaik1203/Roboshop/_git/'+j)
                            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
                        }
                    }
                    'branches' {
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/tags/*')
                        }
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/main')
                        }
                    }
                }
                'scriptPath'('Jenkinsfile')
                'lightweight'(true)
            }
        }
    }
}