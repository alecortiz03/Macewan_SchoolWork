# ALEC ORTIZ - CMPT 370 - 3114013 - CYLINDER.PY
# PROGRAM: CREATE A SPHERE WITH NORMALS AND WITHOUT NORMALS 
# SPHERE.PY

#====== MODULES =====================================================
import math 
#===================================================================

#====== WRITE FILE =================================================
'''
Write content to file  
PARAMETERS: FILE NAME - File to write content to - STRING | CONTENT - Content to write to file - STRING 
RETURN: N.A
'''
def writeFile(filename, content):
    # Open file in append mode 
    with open(filename, "a") as file:
        # Write content to file 
        file.write(content)
#=====================================================================

#====== VERTICES =====================================================
def vertices(filename):
    # Set upDown to 0
    upDown = 0
    # Write title 
    writeFile(filename, f"#Vertices\n\n")
    # Set i to 1 
    i = 1
    # While upDown is less than 180 - 11.25
    while(upDown < 180 - 11.25):
        # Increment upDown by 11.25
        upDown += 11.25
        # Set left right to 0
        leftRight = 0
        # While Left right less than 360
        while (leftRight < 360):
            # Calculate x, y, z coordinates
            x = math.cos(math.radians(leftRight)) * math.sin(math.radians(upDown))
            y = math.cos(math.radians(upDown))
            z = math.sin(math.radians(leftRight)) * math.sin(math.radians(upDown))
            # Write vertex to file
            writeFile(filename, f"v {x} {y} {z} #Vertice {i}\n")
            i += 1
            # Increment leftRight by 11.25
            leftRight += 11.25
        
    # Write top and bottom vertices
    writeFile(filename, f"v 0 1 0 # Vertice {i}\n")
    i += 1
    writeFile(filename, f"v 0 -1 0 #Vertice {i}\n")
        
def faces(filename):
    # Write title
    writeFile(filename, f"\n# FACES\n\n")
    n = 1
    # Loop through layers
    while (n < 17):
        i = 0
        # Loop through vertices in layer
        while(i < 32):
            i += 1
            # Break if at the end of the layer
            if (((n *32) + i) % 32 == 0):
                break
            # Break if at the last vertex
            if (((n *32) + i) == 448):
                break
            # Write faces to file
            writeFile(filename, f"f {(n *32) + i} {((n + 1) * 32) + (i + 1)} {((n + 1) * 32) + i}\n")
            writeFile(filename, f"f {(n *32) + i} {(n *32) + (i + 1)} {((n + 1) * 32) + (i + 1)} \n")
        if (((n *32) + i) == 448):
                break   
        n += 1
    i = 31
    j = 63
    
    # Loop through the last layer
    while (i > 0):
        if (i - 1 == 0):
            break
        # Write faces to file
        writeFile(filename, f"f {j} {j - 1} {i}\n")
        writeFile(filename, f"f {j - 1} {i - 1} {i}\n") 
        i -= 1
        j -= 1
    # Write final faces
    writeFile(filename, f"f 33 64 1\n")
    writeFile(filename, f"f 33 64 1\n")
    writeFile(filename, f"f 64 63 1\n")
    writeFile(filename, f"f 63 31 1\n")

def buildNorthPole(filename):
    # Write title
    writeFile(filename, f"\n# Build North Pole\n\n")
    i = 1
    # Loop through vertices around the north pole
    while (i < 32):
        if(i + 1 == 32):
            break
        # Write faces to file
        writeFile(filename, f"f {i + 1} {i} 481\n")
        i += 1
    # Write final face
    writeFile(filename, f"f 1 31 481\n")

def buildSouthPole(filename):
    # Write title
    writeFile(filename, f"\n# Build South Pole\n\n")
    i = 480
    # Loop through vertices around the south pole
    while (i > 449):
        if (i - 1 == 447):
            break
        # Write faces to file
        writeFile(filename, f"f {i - 1} {i} 482\n")
        i -= 1
    # Write final face
    writeFile(filename, f"f 480 449 482\n")

def buildFinalStrip(filename):
    # Write title
    writeFile(filename, f"\n# Build Final Strip\n\n")
    i = 33
    # Loop through the final strip of vertices
    while (i < 498):
        if ((i + (32 * 2)) - 1 > 480):
            break
        if (i + 32 > 480):
            break;
        # Write faces to file
        writeFile(filename, f"f {i + 32} {i + 31} {i}\n")
        writeFile(filename, f"f {i + 32} {(i + (32 * 2)) - 1} {i + 31}\n")
        i += 32

def normals(filename):
    # Set upDown to 0
    upDown = 0
    # Write title
    writeFile(filename, f"#Normals\n\n")
    # Set i to 1
    i = 1
    # While upDown is less than 180 - 11.25
    while(upDown < 180 - 11.25):
        # Increment upDown by 11.25
        upDown += 11.25
        # Set left right to 0
        leftRight = 0
        # While Left right less than 360
        while(leftRight < 360):
            # Calculate x, y, z coordinates
            x = math.cos(math.radians(leftRight)) * math.sin(math.radians(upDown))
            y = math.cos(math.radians(upDown))
            z = math.sin(math.radians(leftRight)) * math.sin(math.radians(upDown))
            # Write normal to file
            writeFile(filename, f"vn {x} {y} {z} #Normal {i}\n")
            i += 1
            # Increment leftRight by 11.25
            leftRight += 11.25
        
    # Write top and bottom normals
    writeFile(filename, f"vn 0 1 0 #Normal {i}\n")
    i += 1
    writeFile(filename, f"vn 0 -1 0 #Normal {i}\n")

def facesNormals(filename):
    # Write title
    writeFile(filename, f"\n# Normals \n\n")
    n = 1
    # Loop through layers
    while (n < 17):
        i = 0
        # Loop through vertices in layer
        while(i < 32):
            i += 1
            # Break if at the end of the layer
            if (((n *32) + i) % 32 == 0):
                break
            # Break if at the last vertex
            if (((n *32) + i) == 448):
                break
            # Write faces with normals to file
            writeFile(filename, f"f {(n *32) + i}//{(n *32) + i} {((n + 1) * 32) + (i + 1)}//{((n + 1) * 32) + (i + 1)} {((n + 1) * 32) + i}//{((n + 1) * 32) + i}\n")
            writeFile(filename, f"f {(n *32) + i}//{(n *32) + i} {(n *32) + (i + 1)}//{(n *32) + (i + 1)} {((n + 1) * 32) + (i + 1)}//{((n + 1) * 32) + (i + 1)} \n")
        if (((n *32) + i) == 448):
                break   
        n += 1
    i = 31
    j = 63
    
    # Loop through the last layer
    while (i > 0):
        if (i - 1 == 0):
            break
        # Write faces with normals to file
        writeFile(filename, f"f {j}//{j} {j - 1}//{j - 1} {i}//{i}\n")
        writeFile(filename, f"f {j - 1}//{j - 1} {i - 1}//{i - 1} {i}//{i}\n") 
        i -= 1
        j -= 1
    # Write final faces with normals
    writeFile(filename, f"f 33//33 64//64 1//1\n")
    writeFile(filename, f"f 33//33 64//64 1//1\n")
    writeFile(filename, f"f 64//64 63//63 1//1\n")
    writeFile(filename, f"f 63//63 31//31 1//1\n")

def buildNorthPoleNormals(filename):
    # Write title
    writeFile(filename, f"\n# Build North Pole - Normals\n\n")
    i = 1
    # Loop through vertices around the north pole
    while (i < 32):
        if(i + 1 == 32):
            break
        # Write faces with normals to file
        writeFile(filename, f"f {i + 1}//{i + 1} {i}//{i} 481//481\n")
        i += 1
    # Write final face with normals
    writeFile(filename, f"f 1//1 31//31 481//481\n")

def buildSouthPoleNormals(filename):
    # Write title
    writeFile(filename, f"\n# Build South Pole - Normals\n\n")
    i = 480
    # Loop through vertices around the south pole
    while (i > 449):
        if (i - 1 == 447):
            break
        # Write faces with normals to file
        writeFile(filename, f"f {i - 1}//{i - 1} {i}//{i} 482//482\n")
        i -= 1
    # Write final face with normals
    writeFile(filename, f"f 480//480 449//449 482//482\n")

def buildFinalStripNormals(filename):
    # Write title
    writeFile(filename,f"\n# Build Final Strip - Normals\n\n")
    i = 33
    # Loop through the final strip of vertices
    while (i < 498):
        if ((i + (32 * 2)) - 1 > 480):
            break
        if (i + 32 > 480):
            break;
        # Write faces with normals to file
        writeFile(filename, f"f {i + 32}//{i + 32} {i + 31}//{i + 31} {i}//{i}\n")
        writeFile(filename, f"f {i + 32}//{i + 32} {(i + (32 * 2)) - 1}//{(i + (32 * 2)) - 1} {i + 31}//{i + 32}\n")
        i += 32
    
def sphereNoNormals():
    # Create and close the file
    file = open("Sphere.obj", "w")
    file.close()
    # Generate vertices and faces without normals
    vertices("Sphere.obj")
    faces("Sphere.obj")
    buildNorthPole("Sphere.obj")
    buildSouthPole("Sphere.obj")
    buildFinalStrip("Sphere.obj")

def sphereNormals():
    # Create and close the file
    file = open("SphereNormals.obj", "w")
    file.close()
    # Generate vertices, normals, and faces with normals
    vertices("SphereNormals.obj")
    normals("SphereNormals.obj")
    facesNormals("SphereNormals.obj")
    buildNorthPoleNormals("SphereNormals.obj")
    buildSouthPoleNormals("SphereNormals.obj")
    buildFinalStripNormals("SphereNormals.obj")
    
if __name__ == '__main__':
    sphereNoNormals()
    sphereNormals()
