package uguess.qucai.com.merchant.framework.image;


import uguess.qucai.com.merchant.framework.event.EventArgs;

public class PhotoDownloadCompleteEventArgs extends EventArgs {

    private Photo photo = null;
    private Photo.Size size = null;
    private boolean success = true;

    public PhotoDownloadCompleteEventArgs(Photo photo, Photo.Size size) {
        this.photo = photo;
        this.size = size;
    }

    public PhotoDownloadCompleteEventArgs(Photo photo, Photo.Size size, boolean success) {
        this.photo = photo;
        this.size = size;
        this.success = success;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Photo.Size getSize() {
        return size;
    }

    public boolean isSuccess() {
        return success;
    }
}
