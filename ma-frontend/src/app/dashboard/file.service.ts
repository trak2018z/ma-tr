import {Injectable} from "@angular/core";
import {AuthService} from "../common/auth.service";
import {HttpClient} from "@angular/common/http";

export interface FileMetadata {
  url: string;
  fileName: string;
  contentType: string;
  type: string;
  thumbNail: string;
  uploadDate: Date;
}

@Injectable()
export class FileService {

  constructor(private http: HttpClient) {

  }

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

  remove(dashboardUrl: string, fileMetadata: FileMetadata) {
    return this.http.delete(dashboardUrl + '/files/' + fileMetadata.url);
  }

  download(file: FileMetadata) {
    let file_path = '/api/files/' + file.url + '/' + file.fileName;
    let a = <any>document.createElement('A');
    a.href = file_path;
    a.download = file_path.substr(file_path.lastIndexOf('/') + 1);
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  }
}
