package mn.openapi.content.controller

import groovy.transform.CompileStatic
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.http.multipart.CompletedFileUpload
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Controller('/file')
@CompileStatic
class FileController {

    @Post(value = "/", consumes = MediaType.MULTIPART_FORM_DATA)
    @Status(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = 'UploadFile',
            summary = 'Upload a file',
            requestBody = @RequestBody(
                    description = 'File request',
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(type = 'object'))
            ),
            responses = [
                    @ApiResponse(responseCode = '204', description = 'OK'),
            ]
    )
    void uploadFile(CompletedFileUpload file) {
        assert file.bytes
    }

}
