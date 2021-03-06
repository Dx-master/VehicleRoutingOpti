/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.vehiclerouting.plugin.rest.model;

import java.util.List;
import java.util.Objects;

import org.optaweb.vehiclerouting.domain.Route;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Vehicle {@link Route route} representation convenient for marshalling.
 */
class PortableRoute {

    private final PortableVehicle vehicle;
    private final PortableLocation depot;
    private final List<PortableLocation> visits;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private final List<List<PortableCoordinates>> track;

    private String routeDistance = "0";

    PortableRoute(
            PortableVehicle vehicle,
            PortableLocation depot,
            List<PortableLocation> visits,
            List<List<PortableCoordinates>> track) {
        this.vehicle = Objects.requireNonNull(vehicle);
        this.depot = Objects.requireNonNull(depot);
        this.visits = Objects.requireNonNull(visits);
        this.track = Objects.requireNonNull(track);
    }

    PortableRoute(
            PortableVehicle vehicle,
            PortableLocation depot,
            List<PortableLocation> visits,
            List<List<PortableCoordinates>> track,
            double routeDistance) {
        this.vehicle = Objects.requireNonNull(vehicle);
        this.depot = Objects.requireNonNull(depot);
        this.visits = Objects.requireNonNull(visits);
        this.track = Objects.requireNonNull(track);
        this.routeDistance = String.valueOf(routeDistance);
    }

    public PortableVehicle getVehicle() {
        return vehicle;
    }

    public PortableLocation getDepot() {
        return depot;
    }

    public List<PortableLocation> getVisits() {
        return visits;
    }

    public List<List<PortableCoordinates>> getTrack() {
        return track;
    }

    public String getRouteDistance() {
        return routeDistance;
    }
}
