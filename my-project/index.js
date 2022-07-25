const express = require('express')
const app = express()
const fileUpload = require('express-fileupload')

const AWS = require('aws-sdk')
AWS.config.update({
     accessKeyId: 'Enter access KeyId',
     secretAccessKey: 'Enter secret Access Key'
     })
    
     const s3 = new AWS.S3({ params: { Bucket: 'audio-project-input-1' }});

app.use(fileUpload({
 
  limits: { fileSize: 10000 * 1024 * 1024 },
  abortOnLimit: true
}));

app.get('/', (req, res) => {
  res.sendFile('views/audio-project-1.html' , { root : __dirname});
})


app.post('/submit', async (req, res) => {
  const {body, files} = req
  try {
    const upload = new AWS.S3.ManagedUpload({
      params: { 
        
        Body: files.audio.data, 
        
        Key: files.audio.name,
        
        ACL: 'public-read'
      },
      
      service: s3,
    })

    const response = await upload.promise()

    res.send(response)
  } catch (error) {
    res.send(error)
  }
})


const port = 3031

app.listen(port, () => {
  console.log(`Application is available at http://localhost:${port}`)
})