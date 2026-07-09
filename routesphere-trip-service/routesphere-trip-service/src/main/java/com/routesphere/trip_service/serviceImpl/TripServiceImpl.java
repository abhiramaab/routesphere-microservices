package com.routesphere.trip_service.serviceImpl;

import com.routesphere.trip_service.clients.VehicleClient;
import com.routesphere.trip_service.dto.request.CreateTripRequest;
import com.routesphere.trip_service.dto.request.SearchRequest;
import com.routesphere.trip_service.dto.response.TripResponse;
import com.routesphere.trip_service.dto.response.VehicleResponse;
import com.routesphere.trip_service.entity.Trip;
import com.routesphere.trip_service.enums.TripStatus;
import com.routesphere.trip_service.exception.NotFoundException;
import com.routesphere.trip_service.repository.TripRepository;
import com.routesphere.trip_service.service.TripService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final VehicleClient vehicleClient;

    @Override
    public TripResponse createTrip(CreateTripRequest request) {
        VehicleResponse vehicle = vehicleClient.getVehicle(request.getVehicleId());
        if(vehicle == null) {
            throw new NotFoundException("Vehicle with id : " + vehicle.getVehicleId() + " not found");
        }
        Trip trip = new Trip();
        trip.setTripNumber(request.getTripNumber());
        trip.setTripStatus(request.getTripStatus());
        trip.setStartLocation(request.getStartLocation());
        trip.setEndLocation(request.getEndLocation());
        trip.setStartedAt(LocalDateTime.now());
        trip.setDistance(request.getDistance());
        trip.setVehicleId(request.getVehicleId());
        trip.setShipmentId(request.getShipmentId());
        trip.setInvoiceId(request.getInvoiceId());
        trip.setVehicleNumber(vehicle.getVehicleNumber());
        Trip savedTrip = tripRepository.save(trip);
        return mapToTripResponse(savedTrip);
    }

    @Override
    public TripResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip with id : " + id + " not found"));
        return mapToTripResponse(trip);
    }

    @Override
    public TripResponse updateTripById(Long id, CreateTripRequest request) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip with id : " + id + "not found"));
        VehicleResponse vehicle = vehicleClient.getVehicle(request.getVehicleId());
        if(vehicle == null) {
            throw new NotFoundException("Vehicle with id : " + vehicle.getVehicleId() + " not found");
        }
        trip.setTripStatus(request.getTripStatus());
        trip.setStartLocation(request.getStartLocation());
        trip.setEndLocation(request.getEndLocation());
        trip.setVehicleNumber(vehicle.getVehicleNumber());
        trip.setDistance(request.getDistance());
        trip.setTripNumber(request.getTripNumber());
        trip.setVehicleId(request.getVehicleId());
        trip.setShipmentId(request.getShipmentId());
        trip.setInvoiceId(request.getInvoiceId());

        if (request.getTripStatus() == TripStatus.ENDED
                && trip.getEndedAt() == null) {

            trip.setEndedAt(LocalDateTime.now());
        }
        Trip savedTrip = tripRepository.save(trip);
        return mapToTripResponse(savedTrip);
    }

    @Override
    public String deleteTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip with id : " + id + " not found"));
        tripRepository.delete(trip);
        return "Trip has been deleted : " + trip.getTripNumber();
    }



    @Override
    public List<TripResponse> searchTrip(SearchRequest request) {
        return tripRepository.findAll().stream()
                .filter(trip -> request.getTripId() == null ||
                        Objects.equals(trip.getId(), request.getTripId()))

                .filter(trip -> request.getTripNumber() == null ||
                        Objects.equals(trip.getTripNumber(), request.getTripNumber()))

                .filter(trip -> request.getTripStatus() == null ||
                        trip.getTripStatus() == request.getTripStatus())

                .filter(trip -> request.getDistance() == null ||
                        Objects.equals(trip.getDistance(), request.getDistance()))

                .filter(trip -> request.getEndLocation() == null ||
                        Objects.equals(trip.getEndLocation(), request.getEndLocation()))

                .filter(trip -> request.getInvoiceId() == null ||
                        Objects.equals(trip.getInvoiceId(), request.getInvoiceId()))

                .filter(trip -> request.getShipmentId() == null ||
                        Objects.equals(trip.getShipmentId(), request.getShipmentId()))

                .filter(trip -> request.getEndDate() == null ||
                        Objects.equals(trip.getEndedAt(), request.getEndDate()))

                .filter(trip -> request.getStartDate() == null ||
                        Objects.equals(trip.getStartedAt(), request.getStartDate()))

                .filter(trip -> request.getStartLocation() == null ||
                        Objects.equals(trip.getStartLocation(), request.getStartLocation()))

                .filter(trip -> request.getVehicleId() == null ||
                        Objects.equals(trip.getVehicleId(), request.getVehicleId()))

                .filter(trip -> request.getVehicleNumber() == null ||
                        Objects.equals(trip.getVehicleNumber(), request.getVehicleNumber()))

                .map(this::mapToTripResponse)
                .toList();
    }

    private TripResponse mapToTripResponse(Trip trip) {
        TripResponse tripResponse = new TripResponse();
        tripResponse.setTripId(trip.getId());
        tripResponse.setTripNumber(trip.getTripNumber());
        tripResponse.setTripStatus(trip.getTripStatus());
        tripResponse.setStartLocation(trip.getStartLocation());
        tripResponse.setEndLocation(trip.getEndLocation());
        tripResponse.setStartDate(trip.getStartedAt());
        tripResponse.setEndDate(trip.getEndedAt());
        tripResponse.setDistance(trip.getDistance());

        tripResponse.setInvoiceId(trip.getInvoiceId());
        tripResponse.setVehicleId(trip.getVehicleId());
        tripResponse.setShipmentId(trip.getShipmentId());
        tripResponse.setTripStatus(trip.getTripStatus());
        tripResponse.setVehicleId(trip.getVehicleId());
        tripResponse.setVehicleNumber(trip.getVehicleNumber());
        return tripResponse;
    }

    @Override
    public Page<TripResponse> findAllTrip(Pageable pageable) {
        return tripRepository.findAll(pageable).map(this::mapToTripResponse);
    }

    @Override
    public List<TripResponse> findVehicleById(Long vehicleId) {
        return tripRepository.findByVehicleId(vehicleId).stream().map(this::mapToTripResponse).toList();
    }

    @Override
    public List<TripResponse> findByVehicleNumber(String vehicleNumber) {
        return tripRepository.findByVehicleNumber(vehicleNumber).stream().map(this::mapToTripResponse).toList();
    }
}
