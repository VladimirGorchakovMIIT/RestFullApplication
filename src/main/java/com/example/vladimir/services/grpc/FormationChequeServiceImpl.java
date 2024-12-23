package com.example.vladimir.services.grpc;

import com.example.vladimir.models.Company;
import com.example.vladimir.models.Review;
import com.example.vladimir.services.rabbitmq.MessageSender;
import com.google.protobuf.ProtocolStringList;
import com.vladimir.grpc.GreetingServiceGrpc;
import com.vladimir.grpc.HelloWorldProto;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationChequeServiceImpl {
    private final MessageSender messageSender;

    @GrpcClient("client-grpc")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceBlockingStub;

    public String getInformationCheque(String name, String description, Company company) {
        String result = "";

        try{
            HelloWorldProto.FormationChequeRequest request = HelloWorldProto.FormationChequeRequest.newBuilder()
                    .setName(name)
                    .setDescription(description)
                    .addAllTitleReviews(getAllReviewsTitle(company))
                    .build();

            HelloWorldProto.FormationChequeResponse response = greetingServiceBlockingStub.formationCheque(request);

            result = response.getFormationCheque();
        }catch (Exception exception){
            result = "Выбросилось исключение: " + exception.getMessage();
        }

        return result;
    }

    private Iterable<String> getAllReviewsTitle(Company company) {
        List<String> reviewsTitle = new ArrayList<>();

        for (Review review : company.getReviews())
            reviewsTitle.add(review.getTitle());

        return reviewsTitle;
    }

    private StringBuilder formatingText(Company company) {
        List<Review> reviews = company.getReviews();
        StringBuilder result = new StringBuilder();

        for (Review review : reviews)
            result.append(review.getTitle()).append(", ");

        return result;
    }

    public void createQueue(Company company){
        messageSender.send("Name: " + company.getName()
                + ", " + "Description: "
                + company.getDescription()
                + formatingText(company));
    }
}
