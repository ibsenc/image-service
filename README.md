#Image Service
## Upload and Download Images to AWS S3 Using Spring Boot

### Description:
A Spring Boot program which allows for image upload, download, and deletion of files to Amazon S3 (simple storage service).

### Reference:
The tutorial that was used to generate this project can be found here:
https://www.youtube.com/watch?v=vY7c7k8xmKE&ab_channel=JavaTechie

### Setup:
#### application.properties (optional):
- Server port: You can set the server port (currently set to port 8081)
- Max File Size: You can set the max file size (currently set to 2MB)
- Max Request Size: You can set the max request size (currently set to (currently set to 3MB)

#### application.yml:
- Region: Set the region of your AWS S3 bucket (currently set to us-west-2)
- Bucket Name: Set the name of your AWS S3 bucket

#### Environment Variables:
- Create an environment variable containing your AWS access key using command: `export AWS_ACCESS_KEY=your_access_key`
- Create an environment variable containing your AWS secret key using command: `export AWS_SECRET_KEY=your_secret_key`

#### Postman:
- Set up a new Postman environment using url "http://localhost:8081" (or server port of choice)
- Upload Image Endpoint:
    - Method: `POST`
    - Path: `/file/upload`
    - Set Body to `form-data` and create parameter:
        - Key: `file`
        - Select type "File" instead of "Text"
        - Value: select your image
    
- Get Image Endpoint:
    - Method: `GET`
    - Path: `/file/download/:fileName`
    - Path variable:
        - Key: `fileName`
        - Value: `example_image.jpeg`

- Delete Image Endpoint:
    - Method: `DELETE`
    - Path: `/file/delete/:fileName`
    - Path variable:
        - Key: `fileName`
        - Value: `example_image.jpeg`
    
### Clean, Build, and Run the Program:
#### To clean the project:
`./gradlew clean`

#### To build the project:
`./gradlew build`

#### To start the server:
`./gradlew bootrun`# image-service
