import http from "@/http-common";

class UploadFilesService {
  upload(data: Object) {
    //let formData = new FormData();

    //formData.append("image", data.imagedata);
    //formData.append("pet", data.pet);

    return http.post("/pets", data, {
      headers: {
        //"Content-Type": "application/json"
        "Content-Type": "multipart/form-data"
      }
    });
  }

}

export default new UploadFilesService();