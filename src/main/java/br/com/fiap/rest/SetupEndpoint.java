package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.model.Reserva;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
public class SetupEndpoint {
	
	private ReservaDAO dao = new ReservaDAO();

	@GET
	public List<Reserva> index() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public Response create(Reserva setup) {
	
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(setup);

			return Response.status(Response.Status.CREATED).entity(setup).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") int id) {
		if (id == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Reserva setup = dao.findById(id);
		if (setup == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(setup).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Reserva reserva) {
		if (id == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (reserva == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		reserva.setId(id);
		try {
			dao.update(reserva);
			return Response.status(Response.Status.OK).entity(reserva).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	@DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response destroy(@PathParam("id") int id) {
        if ( id == 0 ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (dao.findById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();           
        }

        Reserva reserva = dao.findById(id);

        try {
            dao.destroy(reserva);
            return Response.status(Response.Status.OK).entity(reserva).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(reserva).build();
        }       
    }
}
