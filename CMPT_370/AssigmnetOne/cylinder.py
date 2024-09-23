# ALEC ORTIZ - CMPT 370 - 3114013 - CYLINDER.PY
# PROGRMAM: CREATE A CYLINDER WITH NORMALS AND WITHOUT NORMALS 
# CYLINDER.PY


#====== MODULE ====================
import math 
#================================================================

#====== CYLINDER NORMALS ======
'''
Create cylinder with normals. 
PAMETERS: N/A
RETURN: N.A
'''
def cylinderNormals():
    # Open file CylinderNormals.obj to create file
    file = open("CylinderNormals.obj", "w")
    # Close file
    file.close()
    # Calculate the top cylinder vertices
    calcTopCylinder("CylinderNormals.obj")
    # Calculate top cylinder normals
    calcTopCylinderNormals("CylinderNormals.obj")
    # Calcualte bottom clyinder vertices
    calcBottomCylinder("CylinderNormals.obj")
    # Calcualte bottom cylinder normals
    calcBottomCylinderNormals("CylinderNormals.obj")
    # Build the top face of the cylinder
    buildTopNormals("CylinderNormals.obj")
    # Build the top face of cylinder
    buildBottomNormals("CylinderNormals.obj")
    # Build the surrounding side face of the cylinder
    buildSidesNormals("CylinderNormals.obj")
#============================================================


#====== CYLINDER NO NORMALS =================================
'''
Create cylinder without normals
PAMETERS: N/A
RETURN: N.A
'''
def cylinderNoNormals():
   # Open file Cylninder.obj | Create file
    file = open("Cylinder.obj", "w")
    # Close file
    file.close()
    # Create top cylinder vertices
    calcTopCylinder("Cylinder.obj")
    # Create bottom cylinder vertices
    calcBottomCylinder("Cylinder.obj")
    # Build top top face of cylinder
    buildTop("Cylinder.obj")
    # Buiild bottom face of cylinder
    buildBottom("Cylinder.obj")
    # Build surrounding side face of cylinder
    buildSides("Cylinder.obj")
#==============================================================

#====== WRITE FILE ============================================
'''
Write content into a file of your choice
PAMETERS: FILENAME - File to write into - STRING | CONTENT: Content to write into file - STRING 
RETURN: N.A
'''
def writeFile(filename, content):
    # Open file in append mode 
    with open(filename, "a") as file:
        # Write content into file 
        file.write(content)
#=================================================================


#====== CALC TOP CYLINDER ========================================
'''
Create top vertices for cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def calcTopCylinder(filename):
    # Write title 
    writeFile(filename, "#Vertices - Top\n\n")
    # Set v to 1 
    v = 1
    # Set i to 0
    i = 0
    # Write top center vertice
    writeFile(filename, f"v 0 1 0 #Vertice {v}\n")
    # Increment v 
    v += 1
    # Set x to cosine of i 
    x = math.cos(math.radians(i))
    # Set z to sin of i 
    z = math.sin(math.radians(i))
    # Write vertice into file 
    writeFile(filename, f"v {x} 1 {z} #Vertice {v}\n")
    # Increment i by 11.25
    i += 11.25
    # Increment v by 1
    v += 1
    # While i less than 360
    while i < 360:
        # Set x to cosineof i
        x = math.cos(math.radians(i))
        # Set z to sin of i 
        z = math.sin(math.radians(i))
        # Write vertice and vertice numnber 
        writeFile(filename, f"v {x} 1 {z} #Vertice {v}\n")
        # Increment i by 11.25
        i += 11.25
        # Increment v by 1
        v += 1
#===================================================================


#====== CALC TOP CYLINDER NOMALS ===========================
'''
Create normals for top of cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def calcTopCylinderNormals(filename):
    # Wrtie Title
    writeFile(filename, "#Normals - Top\n\n")
    # Set v to 1
    v = 1
    # Set i to 0
    i = 0
    # Write Normal for top of cylinder 
    writeFile(filename, f"vn 0 1 0 #Normal {v}\n")
    # Increment v by 1
    v += 1
    # Set x to cosine of i 
    x = math.cos(math.radians(i))
    # Set z to sin of i 
    z = math.sin(math.radians(i))
    # Write normals with z and x calculated 
    writeFile(filename, f"vn {x} 1 {z} #Normal {v}\n")
    # Increment i by 11.25
    i += 11.25
    # Increment v by 1
    v += 1
    # While i less than 360 
    while i < 360:
        # Set x to the cosine of i 
        x = math.cos(math.radians(i))
        # Set z to the sin of i 
        z = math.sin(math.radians(i))
        # Write normals with z and x calculated 
        writeFile(filename, f"vn {x} 1 {z} #Normal {v}\n")
        # Increment i by 11.25
        i += 11.25
        # Increment v by 1 
        v += 1
#=========================================================================


#====== CALC BOTTOM CYLINDER  ====================================
'''
Create bottom vertices for cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def calcBottomCylinder(filename):
    # Write Title 
    writeFile(filename, "\n#Vertices - Bottom\n\n")
    # Set v to 34
    v = 34
    # Set i to 0
    i = 0
    # Write Vertice for bottom center 
    writeFile(filename, f"v 0 -1 0 #Vertice {v}\n")
    # Increment v by 1 
    v += 1
    # Set x to cosine of i 
    x = math.cos(math.radians(i))
    # Set z to sin of i 
    z = math.sin(math.radians(i))
    # Write vertice with x and z calculated 
    writeFile(filename, f"v {x} -1 {z} #Vertice {v}\n")
    # Increment i by 11.25
    i += 11.25
    # Increment v by 1 
    v += 1
    # While i is less than 360
    while i < 360:
        # Set x to cosine of i 
        x = math.cos(math.radians(i))
        # Set z to sin i 
        z = math.sin(math.radians(i))
        # Write vertice with z and x calcualted 
        writeFile(filename, f"v {x} -1 {z} #Vertice {v}\n")
        # Increment i by 11.25 
        i += 11.25
        # Increment v by 1 
        v += 1
#================================================================================



#====== CALC BOTTOM CYLINDER NORMALS ==========================================
'''
Create bottom normals of cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def calcBottomCylinderNormals(filename):
    # Write title 
    writeFile(filename, "\n#Normals - Bottom\n\n")
    # Set v to 34 
    v = 34
    # Set i to 0
    i = 0
    # Write bottom face normal 
    writeFile(filename, f"vn 0 -1 0 #Normal {v}\n")
    # Increment v by 1
    v += 1
    # Set x to cosine of i 
    x = math.cos(math.radians(i))
    # Set z to sin of i 
    z = math.sin(math.radians(i))
    # Write normal with z and x calcualted 
    writeFile(filename, f"vn {x} -1 {z} #Normal {v}\n")
    # Increment i by 11.25 
    i += 11.25
    # Increment v by 1
    v += 1
    # While i is less than 360
    while i < 360:
        # Set x to the cosine of i 
        x = math.cos(math.radians(i))
        # Set z to sin of i 
        z = math.sin(math.radians(i))
        # Write normal with z and x calculated 
        writeFile(filename, f"vn {x} -1 {z} #Normal {v}\n")
        # Increment i by 11.25
        i += 11.25
        # Increment v by 1 
        v += 1
#===============================================================================


#====== BUILD TOP ========================================================
'''
Build top face of cylinder 
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildTop(filename):
    # Set i to 1
    i = 1
    # Write title 
    writeFile(filename, "\n#Top Face\n\n")
    # While i is less than/equal to 33
    while i <= 33:
        # If i + 1 is eqal to 34
        if (i + 1 == 34):
            # Connect face at vertice 1 2 and i 
            writeFile(filename, f"f 1 2 {i}\n")
        # Else 
        else:
            # Write file to connect face 
            writeFile(filename, f"f 1 {i + 1} {i}\n")
        # Increment i by 1
        i += 1;
#=============================================================================


#====== BUILD TOP NORMALS ===================================================
'''
Build top face with normals 
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildTopNormals(filename):
    # Set i to 1 
    i = 1
    # Write title 
    writeFile(filename, "\n#Top Face\n\n")
    # While i is less than.equal to 33
    while i <= 33:
        # If i + 1 is equal to 34
        if (i + 1 == 34):
            # Write face and normals 1//1 2//2 i//i
            writeFile(filename, f"f 1//1 2//2 {i}//{i}\n")
        # Else 
        else:
            # Write face with normals 
            writeFile(filename, f"f 1//1 {i + 1}//{i + 1} {i}//{i}\n")
        # Increment i by 1
        i += 1;
#==================================================================================


#====== BUILD BOTTOM =============================================================
'''
Build bottom face of cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildBottom(filename):
    # Set i to 34 
    i = 34
    # Write ttle 
    writeFile(filename, "\n#Bottom Face\n\n")
    # While i is less than/equal to 66
    while i <= 66:
        # If i + i is equal to 67
        if (i + 1 == 67):
            # Write face at 34 i 35
            writeFile(filename, f"f 34 {i} 35\n")
        # Else
        else:
            # Write face 
            writeFile(filename, f"f 34 {i} {i + 1}\n")
        # Increment i by 1 
        i += 1;
#==================================================================================


#====== BUILD TOP NORMALS ==========================================================
'''
Build top of cylinder with normals 
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildBottomNormals(filename):
    # Set i to 34 
    i = 34
    # Write title 
    writeFile(filename, "\n#Bottom Face\n\n")
    # While i is less than/equal to 66
    while i <= 66:
        # If i + 1 is equal to 67
        if (i + 1 == 67):
            # Write face 34//34 i//i 35//35
            writeFile(filename, f"f 34//34 {i}//{i} 35//35\n")
        # Else
        else:
            # Write face wth normals 
            writeFile(filename, f"f 34//34 {i}//{i} {i + 1}//{i + 1}\n")
        # Increment i by 1
        i += 1;
#====================================================================================
'''
Build sides of cylinder
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildSides(filename):
    # Set i to 2 
    i = 2
    # Set j to 35 
    j = 35
    # Write title 
    writeFile(filename, "\n#Side Faces\n\n")
    # While i is less than.equal to 66
    while i <= 66:
        # If i is equal to 33
        if (i == 33):
            # Build left triangle 
            writeFile(filename, f"f {i} 2 {j}\n")
            # Build rght trianlge 
            writeFile(filename, f"f {i} {j} {j - 1}\n")
            # Break 
            break
        # Else 
        else:
            # Build face 
            writeFile(filename, f"f {i} {i + 1} {j}\n")
            # If j is equal to 35
            if j == 35:
                # Build face
                writeFile(filename, f"f {i} {j} 66\n")
            # Else 
            else:
                # Build face 
                writeFile(filename, f"f {i} {j} {j - 1}\n")
        # Increment i by 1 
        i += 1
        # Increment j by 1 
        j += 1
#=================================================================================


#====== BUILD SIDES NORMALS =====================================================
'''
Build sides of cylinder with normals 
PAMETERS: FILENAME - File to write into - STRING 
RETURN: N.A
'''
def buildSidesNormals(filename):
    # Set i to 2 
    i = 2
    # Set j to 35 
    j = 35
    # Write title 
    writeFile(filename, "\n#Side Faces\n\n")
    # While i is less than/equal to 66
    while i <= 66:
        # If i is equal to 33
        if (i == 33):
            # Build left triangle 
            writeFile(filename, f"f {i}//{i} 2//2 {j}//{j}\n")
            # Build right triangle 
            writeFile(filename, f"f {i}//{i} {j}//{j} {j - 1}//{j - 1}\n")
            # Break 
            break
        # Else
        else:
            # Build face 
            writeFile(filename, f"f {i}//{i} {i + 1}//{i + 1} {j}//{j}\n")
            # If j is equal to 35
            if j == 35:
                # Build face 
                writeFile(filename, f"f {i}//{i} {j}//{j} 66//6\n")
            # Else 
            else:
                # Build face 
                writeFile(filename, f"f {i}//{i} {j}//{j} {j - 1}//{j - 1}\n")
        # Increment i by 1 
        i += 1
        # Increment j by 1 
        j += 1
#===============================================================================================

#====== MAIN ==================================================================================
if __name__ == '__main__':
    cylinderNoNormals()
    cylinderNormals()

#====== FIN =====================================================================================
