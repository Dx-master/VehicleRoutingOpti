/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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

package org.optaweb.vehiclerouting.plugin.rest;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.optaweb.vehiclerouting.plugin.rest.model.PortableCoordinates;
import org.optaweb.vehiclerouting.plugin.rest.model.RoutingProblemInfo;
import org.optaweb.vehiclerouting.plugin.rest.model.ServerInfo;
import org.optaweb.vehiclerouting.service.demo.DemoService;
import org.optaweb.vehiclerouting.service.region.BoundingBox;
import org.optaweb.vehiclerouting.service.region.RegionService;

@Path("api/serverInfo")
public class ServerInfoResource {

    private final DemoService demoService;
    private final RegionService regionService;

    @Inject
    public ServerInfoResource(DemoService demoService, RegionService regionService) {
        this.demoService = demoService;
        this.regionService = regionService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ServerInfo serverInfo() {
        BoundingBox boundingBox = regionService.boundingBox();
        List<PortableCoordinates> portableBoundingBox = Arrays.asList(
                PortableCoordinates.fromCoordinates(boundingBox.getSouthWest()),
                PortableCoordinates.fromCoordinates(boundingBox.getNorthEast()));
        List<RoutingProblemInfo> demos = demoService.demos().stream()
                .map(routingProblem -> new RoutingProblemInfo(
                        routingProblem.name(),
                        routingProblem.visits().size()))
                .collect(toList());
        return new ServerInfo(portableBoundingBox, regionService.countryCodes(), demos);
    }
}
