import {Injectable} from "@angular/core";
import {AuthService} from "../common/auth.service";

@Injectable()
export class FileService {

  public upload(dashboardUrl: string, file: File, progress: (progress: number) => any): Promise<any> {
    return new Promise((resolve, reject) => {
      let formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();
      formData.append("file", file, file.name);
      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            resolve(JSON.parse(xhr.response));
          } else {
            reject(xhr.response);
          }
        }
      };
      xhr.upload.onprogress = (event) => {
        let p = Math.round(event.loaded / event.total * 100);
        progress(p);
      };
      let url = dashboardUrl + '/files';
      xhr.open('POST', url, true);
      xhr.setRequestHeader("Authorization", AuthService.getToken());
      xhr.send(formData);
    });
  }
}
