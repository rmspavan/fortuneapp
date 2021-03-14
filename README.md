# Code

mkdir ~/projs
git clone https://github.com/devteds/e9-cloudformation-docker-ecs.git docker-on-ecs
cd docker-on-ecs

# Dockerize a fortuneapp to test
# Run on local
docker build -t books-api ./app/
docker run -it -p 4567:4567 --rm books-api:latest
open http://localhost:4567/
open http://localhost:4567/stat
open http://localhost:4567/api/books


# Push Docker Image to ECR

aws ecr create-repository --repository-name books-api
aws ecr get-login --no-include-email | sh
IMAGE_REPO=$(aws ecr describe-repositories --repository-names books-api --query 'repositories[0].repositoryUri' --output text)
docker tag books-api:latest $IMAGE_REPO:v1
docker push $IMAGE_REPO:v1

# Create CloudFormation Stacks
aws cloudformation create-stack --template-body file://$PWD/infra/vpc.yml --stack-name vpc

aws cloudformation create-stack --template-body file://$PWD/infra/iam.yml --stack-name iam --capabilities CAPABILITY_IAM

aws cloudformation create-stack --template-body file://$PWD/infra/app-cluster.yml --stack-name app-cluster

aws cloudformation create-stack --template-body file://$PWD/infra/api.yml --stack-name api
