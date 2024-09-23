ASSIGNMENT ONE - OBJ 3D MODELS - ALEC ORTIZ - 3114013
------------------------------------------------------

CYLINDER
---------
- The cylinder was divided into three sections: the top, bottom, and surrounding sides. 
- Cylinder.py defines methods such as: calcTopCylinder and calcBottomCylinder to calcualte vertices. 
    -> Vertices were create every follwing a circular curvature, creating a vertice every 11.25 degrees
    -> This split the circle into 32 even triangles 
- Both bottom and top faces of cyclinders contain 32 evenly segemnted triangles to create the faces
- Sides were created using a combination of the top and bottom clinder vertices
    -> Full face sqaures were created by building both left and right halves of sqaure using triangles 
- The order of Vertices starts from top cylinder and then finishes at the bottom cylinder
    -> Faces create the top face first, followed by the bottom, and finishing with the creation of the surrounding side faces
- Faces for the top face are created using a fan pattern
    -> Surroundig side faces are created follwing a sqaure pattern

SPHERE
------
- The sphere was divided into 4 sections: North Pole, South Pole, Surrounding Sides, Final Connecting Strip
- The sphere was divided using longitude and latitiude
- The vertices are arranged into "strips" of latitude circles, starting from the north pole and ending at the south pole, with regular angular spacing.
- The sphere's discretization is linked to its spherical coordinate parametrization (latitude and longitude). Each vertex is positioned based on these coordinates, creating "strips" of triangles that cover the surface of the sphere.
- Points are ordered from the north pole downwards in concentric rings (latitude strips). The north pole is at the top, and the south pole is at the bottom, with vertices arranged radially.
- Triangles are formed by connecting adjacent vertices in each latitude strip. The poles are connected to the nearest latitude circle using triangles.

