# HTML File to PDF

```xml
<dependencies>
    <dependency>
        <!-- ALWAYS required, usually included transitively. -->
        <groupId>com.openhtmltopdf</groupId>
        <artifactId>openhtmltopdf-core</artifactId>
        <version>${openhtml.version}</version>
    </dependency>

    <dependency>
        <!-- Required for PDF output. -->
        <groupId>com.openhtmltopdf</groupId>
        <artifactId>openhtmltopdf-pdfbox</artifactId>
        <version>${openhtml.version}</version>
    </dependency>

    <dependency>
        <!-- Required for image output only. -->
        <groupId>com.openhtmltopdf</groupId>
        <artifactId>openhtmltopdf-java2d</artifactId>
        <version>${openhtml.version}</version>
    </dependency>

    <dependency>
        <!-- Optional, leave out if you do not need SVG support. -->
        <groupId>com.openhtmltopdf</groupId>
        <artifactId>openhtmltopdf-svg-support</artifactId>
        <version>${openhtml.version}</version>
    </dependency>
</dependencies>
```