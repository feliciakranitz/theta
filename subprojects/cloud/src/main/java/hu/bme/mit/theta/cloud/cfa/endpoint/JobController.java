package hu.bme.mit.theta.cloud.cfa.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers.JobsApi;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers.ModelApi;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.AllJobsResponse;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.CexFileResponse;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.InlineResponse200;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

