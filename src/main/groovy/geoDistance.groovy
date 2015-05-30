/**
 * Computes distance in kilometers between point1 (lat1, long1) and point2 (lat2, long2)
 *
 * @param lat1
 * @param long1
 * @param lat2
 * @param long2
 * @return distance in kilometers
 */
double dist(double lat1, double long1, double lat2, double long2) {
    d2r = Math.PI / 180.0
    def MEAN_RADIUS = 6371

    Math.with {
        double dlong = (long2 - long1) * d2r
        double dlat = (lat2 - lat1) * d2r
        double a = pow(sin(dlat / 2.0), 2) + cos(lat1 * d2r) * cos(lat2 * d2r) * pow(sin(dlong / 2.0), 2)
        double c = 2 * atan2(sqrt(a), sqrt(1 - a))
        double d = MEAN_RADIUS * c
        return d
    }
}

println dist(50, 20, 49, 19)
