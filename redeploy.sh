#!/bin/bash

kubectl delete -f k8s/k8s-deployment.yaml

kubectl apply -f k8s/k8s-deployment.yaml
