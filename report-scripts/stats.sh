#!/usr/bin/env bash
export _GIT_SINCE="09-20-2018"

REPOS=(
    "glp-application-config-common"
    "glp-asset-model-service-analytics"
    "glp-assignment-management"
    "glp-assignment-management-bridge"
    "glp-assignment-management-test"
    "glp-autobahn-sdk"
    "glp-bitesize-manifest-files"
    "glp-bridge-revel-assignment"
    "glp-common-files"
    "glp-content-management"
    "glp-content-management-test"
    "glp-core"
    "glp-course-delivery"
    "glp-course-delivery-test"
    "glp-course-management"
    "glp-course-management-test"
    "glp-crosscutting_integration-test"
    "glp-devops-test"
    "glp-engagement-common"
    "glp-glp-devops-"
    "glp-graph-management"
    "glp-iam"
    "glp-iam-test"
    "glp-inbound-message-consumer"
    "glp-inbound-message-consumer-test"
    "glp-integration-test"
    "glp-kafka-test"
    "glp-learner-model-services-analytics"
    "glp-learning-asset-delivery-kernel"
    "glp-learning-asset-delivery-kernel-test"
    "glp-learning-asset-evaluation-kernel"
    "glp-learning-asset-evaluation-kernel-test"
    "glp-learning-asset-provisioning"
    "glp-learning-asset-provisioning-test"
    "glp-learning-experience-composition-kernel"
    "glp-learning-experience-composition-kernel-test"
    "glp-learning-experience-engagement-kernel"
    "glp-learning-experience-engagement-kernel-test"
    "glp-learning-product-builder"
    "glp-learning-product-builder-test"
    "glp-personalized-learning-service-analytics"
    "glp-platform-portal-ui"
    "glp-platform-sdk-ui"
    "glp-platform-uisdk-ui"
    "glp-product-provisioning-tool"
    "glp-registrar"
    "glp-registrar-test"
    "glp-roster-management"
    "glp-roster-management-test"
    "glp-study-plan-service-analytics"
    "glp-telemetry-nfr"
    "graphql-poc"
)

# mkdir repodir
cd repodir

for REPO in "${REPOS[@]}"
do
    # git clone "ssh://git@bitbucket.pearson.com/glpv2/$REPO.git"
    cd $REPO
    git quick-stats detailedGitStats >> results.txt

    while read p
    do
        echo "$p" >> ../../row_data.txt
    done < results.txt

    cd ..
done