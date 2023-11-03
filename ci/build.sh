#!/bin/sh

REPOSITORY="uwas-${CI_COMMIT_BRANCH}"

echo $CONTAINER_REGISTRY_PASSWORD | docker login $CONTAINER_REGISTRY -u $CONTAINER_REGISTRY_USER --password-stdin

echo "Building Image for uwas qyality automation script ...."

docker build -t $CONTAINER_REGISTRY/$REPOSITORY/$IMAGE_NAME:latest .
docker push  $CONTAINER_REGISTRY/$REPOSITORY/$IMAGE_NAME:latest
