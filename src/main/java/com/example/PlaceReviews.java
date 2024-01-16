package com.example;

import com.google.maps.*;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlaceDetails.Review;

public class PlaceReviews {

    public static void reviews(String place) {

        String apiKey = Configuration.getApiKeyMaps();

        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

        String location = place;

        String placeId = getPlaceId(context, location);

        if (placeId != null) {

            try {
                PlaceDetails placeDetailsRequest = PlacesApi.placeDetails(context, placeId).await();

                if (placeDetailsRequest.reviews != null) {
                    for (Review review : placeDetailsRequest.reviews) {
                        System.out.println("Author: " + review.authorName);
                        System.out.println("Rating: " + review.rating);
                        System.out.println("Text: " + review.text);
                        System.out.println("-------------");
                    }
                } else {
                    System.out.println("No reviews found for this place.");
                }

            } catch (Exception e) {
                System.out.println("Could not retrieve reviews for the specified location.");
            }
        } else {
            System.out.println("Could not retrieve reviews for the specified location.");
        }
    }

    private static String getPlaceId(GeoApiContext context, String location) {
        try {
            PlacesSearchResponse results = PlacesApi.textSearchQuery(context, location).await();
            if (results.results != null && results.results.length > 0) {
                return results.results[0].placeId;
            } else {
                System.out.println("No results found for the specified location.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
