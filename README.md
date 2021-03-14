# Code

mkdir ~/projs
git clone https://github.com/devteds/e9-cloudformation-docker-ecs.git docker-on-ecs
cd docker-on-ecs

# Dockerize a fortuneapp to test
# Run on local
docker build -t fortuneapp .
docker run -it -p 8081:8081 --rm fortuneapp:latest
open http://localhost:8081/
open http://localhost:8081/healthcheck
open http://localhost:8081/vi/fortune


# Push Docker Image to ECR

aws ecr create-repository --repository-name fortuneapp
aws ecr get-login --no-include-email | sh

IMAGE_REPO=$(aws ecr describe-repositories --repository-names fortuneapp --query 'repositories[0].repositoryUri' --output text)
docker tag fortuneapp:latest $IMAGE_REPO:v1
docker push $IMAGE_REPO:v1

# Create CloudFormation Stacks
aws cloudformation create-stack --template-body file://$PWD/infra/vpc.yml --stack-name vpc

aws cloudformation create-stack --template-body file://$PWD/infra/iam.yml --stack-name iam --capabilities CAPABILITY_IAM

aws cloudformation create-stack --template-body file://$PWD/infra/app-cluster.yml --stack-name app-cluster

aws cloudformation create-stack --template-body file://$PWD/infra/api.yml --stack-name api
