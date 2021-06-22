/*
 * This file is part of OpenTSDB.
 *  Copyright (C) 2021 Yahoo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express  implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.opentsdb.horizon.resource;

import net.opentsdb.horizon.service.AlertService;
import net.opentsdb.horizon.view.AlertView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Alerts")
@Path("v1/alert")
public class AlertResource {

  private final AlertService service;

  public AlertResource(AlertService service) {
    this.service = service;
  }

  @ApiOperation("Get by id")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response getAlertById(@PathParam("id") long id,
      @QueryParam("definition") @DefaultValue("true") boolean definition,
      @QueryParam("deleted") @DefaultValue("false") boolean deleted) {
    AlertView alert = service.getById(id, definition, deleted);
    return Response.status(Response.Status.OK).entity(alert).build();
  }
}
