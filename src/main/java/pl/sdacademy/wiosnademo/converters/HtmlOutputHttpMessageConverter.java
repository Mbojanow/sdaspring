package pl.sdacademy.wiosnademo.converters;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import pl.sdacademy.wiosnademo.model.SdaCourse;

public class HtmlOutputHttpMessageConverter implements HttpMessageConverter<SdaCourse> {

  private static final String HTML_OUTPUT_TEMPLATE = "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "<head>\n" +
      "    <meta charset=\"UTF-8\">\n" +
      "    <title>Title</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "%s\n" +
      "</body>\n" +
      "</html>";

  @Override
  public boolean canRead(final Class<?> clazz, final MediaType mediaType) {
    return false;
  }

  @Override
  public boolean canWrite(final Class<?> clazz, final MediaType mediaType) {
    return clazz.equals(SdaCourse.class);
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return List.of(MediaType.TEXT_HTML);
  }

  @Override
  public SdaCourse read(final Class<? extends SdaCourse> clazz, final HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    return null;
  }

  @Override
  public void write(final SdaCourse sdaCourse, final MediaType contentType,
                    final HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    final String bodyInnerHtml = Stream.of(sdaCourse.getId(), sdaCourse.getName(), sdaCourse.getDescription(), sdaCourse.getPrice())
        .filter(Objects::nonNull)
        .map(element -> "<p>" + element + "</p>")
        .collect(Collectors.joining("\n"));
    final String htmlOutput = String.format(HTML_OUTPUT_TEMPLATE, bodyInnerHtml);
    outputMessage.getBody().write(htmlOutput.getBytes());
  }
}
