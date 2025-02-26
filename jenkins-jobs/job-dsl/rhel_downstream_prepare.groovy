pipelineJob('rhel-downstream-prepare-job') {
    displayName('AMQ Streams on RHEL Preparation')
    description('Prepares image for AMQ Streams on RHEL')

    properties {
        githubProjectUrl('https://github.com/debezium/debezium')
    }

    parameters {
        stringParam('MAIL_TO', 'jcechace@redhat.com')
//        QUAY CONFIG
        stringParam('QUAY_CREDENTIALS', 'rh-integration-quay-creds', 'Quay.io credentials id')
        stringParam('QUAY_ORGANISATION', '', 'Organisation where images are copied')
//        RHEL CONFIG
        stringParam('RHEL_IMAGE', 'registry.access.redhat.com/ubi8:latest', 'Base RHEL image')
//        KAFKA CONFIG
        stringParam('KAFKA_URL', '', 'AMQ streams kafka')
//        DEBEZIUM CONFIG
        stringParam('DBZ_GIT_REPOSITORY', 'https://github.com/debezium/debezium.git', 'Repository from which Debezium sources are cloned')
        stringParam('DBZ_GIT_BRANCH', 'master', 'A branch/tag of Debezium sources')
//        IMAGE NAME
        stringParam('IMAGE_TAG', '', 'Tag of built image')
//        DEBEZIUM CONNECT IMAGE CONFIG
        textParam('DBZ_CONNECTOR_ARCHIVE_URLS', '', 'List of URLs to productised Debezium connectors')
//        EXTRA CONFIG
        textParam('DBZ_EXTRA_LIBS', '', 'List of extra libraries added to connectors')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins-jobs/pipelines/rhel_downstream_prepare_pipeline.groovy'))
            sandbox()
        }
    }
}
