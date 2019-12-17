#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/api-gateway .

#!docker tag e09252265e6d registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/api-gateway

#!docker push registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/api-gateway
