package com.hieplp.recipe.user.controller;

import com.hieplp.recipe.common.grpc.user.DoesUsernameExistRequest;
import com.hieplp.recipe.common.grpc.user.DoesUsernameExistResponse;
import com.hieplp.recipe.common.grpc.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class UserProtoController extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void doesUsernameExist(DoesUsernameExistRequest request, StreamObserver<DoesUsernameExistResponse> responseObserver) {
        log.info("Check if username {} exists", request.getUserName());
        responseObserver.onNext(DoesUsernameExistResponse.newBuilder().setExists(true).build());
        responseObserver.onCompleted();
    }
}
