package mn.openapi.content.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.multipart.MultipartBody
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class FileControllerSpec extends Specification {


    @Inject
    @Client('/')
    HttpClient httpClient


    void 'upload a file'() {
        given:
        MultipartBody multipartBody = MultipartBody.builder()
                .addPart('file', 'file.txt', MediaType.TEXT_PLAIN_TYPE, 'content'.bytes)
                .build()

        when:
        def req = HttpRequest.POST("/file", multipartBody)
        def res = httpClient.toBlocking().exchange(req.contentType(MediaType.MULTIPART_FORM_DATA_TYPE))

        then:
        res.status() == HttpStatus.NO_CONTENT
    }


}
